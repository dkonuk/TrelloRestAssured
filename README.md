# Trello API Testing Project

## Setup Instructions

### 1. Set Environment Variables

#### On MacOS/Linux:
Open terminal and add these lines to your `~/.bash_profile` or `~/.zshrc`:
```bash
export TRELLO_API_KEY="your_api_key_here"
export TRELLO_TOKEN="your_token_here"
```

#### On Windows:
1. Open System Properties
2. Click "Environment Variables"
3. Add new variables:
   - TRELLO_API_KEY = your_api_key_here
   - TRELLO_TOKEN = your_token_here

### 2. Verify Setup
Run this command in terminal to verify variables are set:
```bash
echo $TRELLO_API_KEY
echo $TRELLO_TOKEN
```

### 3. Run Tests
```bash
mvn clean test
```